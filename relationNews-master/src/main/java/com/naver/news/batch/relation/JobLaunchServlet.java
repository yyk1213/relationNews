package com.naver.news.batch.relation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author yongseog.yun
 * !!이부분은 크게 신경쓰지않아도 됩니다.
 */
public class JobLaunchServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobLaunchServlet.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("jobRegistry")
	private JobLocator jobLocator;

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletOutputStream outStream = response.getOutputStream();

		try {
			String jobName = request.getParameter("job");
			if (StringUtils.isEmpty(jobName)) {
				throw new IllegalArgumentException("job is required.");
			}
			Job job = jobLocator.getJob(jobName);

			JobParametersBuilder builder = new JobParametersBuilder();

			@SuppressWarnings("unchecked") Map<String, String[]> param = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : param.entrySet()) {
				builder.addString((String)entry.getKey(), (String)entry.getValue()[0]);
			}

			LOGGER.info("JobParameters : {}", builder.toJobParameters());
			JobExecution jobExec = jobLauncher.run(job, builder.toJobParameters());
			if (ExitStatus.FAILED.equals(jobExec.getExitStatus())) {
				List<Throwable> failureList = jobExec.getAllFailureExceptions();
				for (Throwable throwable : failureList) {
					LOGGER.error("", throwable);
					IOUtils.write(ExceptionUtils.getFullStackTrace(throwable), outStream);
				}
			}

			String msg = String.format(" Job Execution[ID:%d] finished. ExitStatus is  %s", jobExec.getJobId(),
				jobExec.getExitStatus().getExitCode());

			IOUtils.write(msg, outStream);
		} catch (Exception e) {
			LOGGER.error("", e);
			IOUtils.write(ExceptionUtils.getFullStackTrace(e), outStream);
		} finally {}
	}

}