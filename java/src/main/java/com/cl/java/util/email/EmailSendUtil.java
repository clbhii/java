/*
 * 系统名称: eden 1.0
 * 模块名称: eden.common.email
 * 类 名 称: EmailSendUtil.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.email;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 * 功能说明: <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-5-21 <br>
 * 审核人员: <br>
 * 相关文档: <br>
 * 修改记录: <br>
 * 修改日期 修改人员 修改说明 <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class EmailSendUtil {

	/**
	 * 发送简单的邮件
	 * @param mailServer 邮件服务器地址
	 * @param mailServerPort 邮件服务器端口
	 * @param mailFrom 发件人
	 * @param mailTo  收件人
	 * @param mailCc  抄送人
	 * @param mailBcc 密送人
	 * @param userName 用户名
	 * @param userPwd 用户密码
	 * @param charset 编码
	 * @param subject 邮件主题
	 * @param message 邮件正文（text）
	 * @throws EmailException
	 */
	public static void sendSimpleEmail(String mailServer,
			Integer mailServerPort, String mailFrom, String[] mailTo,
			String[] mailCc, String[] mailBcc, String userName, String userPwd,
			String charset, String subject, String message)
			throws EmailException {
		SimpleEmail email = new SimpleEmail();
		init(email, mailServer, mailServerPort, mailFrom, mailTo, mailCc,
				mailBcc, userName, userPwd, charset, subject);

		email.setMsg(message);
		email.send();
	}
	/**
	 * 发送简单的带附件邮件
	 * @param mailServer 邮件服务器地址
	 * @param mailServerPort 邮件服务器端口
	 * @param mailFrom 发件人
	 * @param mailTo  收件人
	 * @param mailCc  抄送人
	 * @param mailBcc 密送人
	 * @param userName 用户名
	 * @param userPwd 用户密码
	 * @param charset 编码
	 * @param subject 邮件主题
	 * @param message 邮件正文（text）
	 * @param files 附件
	 * @throws EmailException
	 */
	public static void sendMultiPartEmail(String mailServer,
			Integer mailServerPort, String mailFrom, String[] mailTo,
			String[] mailCc, String[] mailBcc, String userName, String userPwd,
			String charset, String subject, String message, File[] files)
			throws EmailException {
		MultiPartEmail email = new MultiPartEmail();
		// 初始化
		init(email, mailServer, mailServerPort, mailFrom, mailTo, mailCc,
				mailBcc, userName, userPwd, charset, subject);

		email.setMsg(message);
		// 添加附件
		attach(email, files);
		email.send();
	}
	/**
	 * 发送html格式的带附件邮件
	 * @param mailServer 邮件服务器地址
	 * @param mailServerPort 邮件服务器端口
	 * @param mailFrom 发件人
	 * @param mailTo  收件人
	 * @param mailCc  抄送人
	 * @param mailBcc 密送人
	 * @param userName 用户名
	 * @param userPwd 用户密码
	 * @param charset 编码
	 * @param subject 邮件主题
	 * @param dataSourceUrl html中资源的路径
	 * @param hmtlMsg 邮件正文（html）
	 * @param files 附件
	 * @throws EmailException
	 */
	public static String sendImageHtmlEmail(String mailServer,
			Integer mailServerPort, String mailFrom, String[] mailTo,
			String[] mailCc, String[] mailBcc, String userName, String userPwd,
			String charset, String subject, URL dataSourceUrl, String htmlMsg,
			File[] files) throws EmailException {
		ImageHtmlEmail email = new ImageHtmlEmail();
		// 初始化
		init(email, mailServer, mailServerPort, mailFrom, mailTo, mailCc,
				mailBcc, userName, userPwd, charset, subject);

		email.setDataSourceResolver(new DataSourceUrlResolver(dataSourceUrl, true));
		email.setHtmlMsg(htmlMsg);
		// 添加附件
		attach(email, files);
		return email.send();
	}
	/**
	 * 发送html格式的带附件邮件
	 * @param mailServer 邮件服务器地址
	 * @param mailServerPort 邮件服务器端口
	 * @param mailFrom 发件人
	 * @param mailTo  收件人
	 * @param mailCc  抄送人
	 * @param mailBcc 密送人
	 * @param userName 用户名
	 * @param userPwd 用户密码
	 * @param charset 编码
	 * @param subject 邮件主题
	 * @param dataSourceUrl html中资源的路径
	 * @param hmtlUrl 邮件正文（html）的路径
	 * @param files 附件
	 * @throws EmailException
	 */
	public static String sendImageHtmlEmail(String mailServer,
			Integer mailServerPort, String mailFrom, String[] mailTo,
			String[] mailCc, String[] mailBcc, String userName, String userPwd,
			String charset, String subject, URL dataSourceUrl, URL htmlUrl, File[] files)
			throws EmailException, IOException {
		String htmlMsg = loadUrlContent(htmlUrl);
		return sendImageHtmlEmail(mailServer, mailServerPort, mailFrom, mailTo,
				mailCc, mailBcc, userName, userPwd, charset, subject, dataSourceUrl,
				htmlMsg, files);

	}
	/**
	 * 加载邮件的正文
	 * @param url 邮件的正文的路径
	 * @return
	 * @throws IOException
	 */
	private static String loadUrlContent(URL url) throws IOException {
		InputStream stream = url.openStream();
		StringBuilder html = new StringBuilder();
		try {
			List<String> lines = IOUtils.readLines(stream);
			for (String line : lines) {
				html.append(line).append("\n");
			}
		} finally {
			stream.close();
		}
		return html.toString();
	}
	/**
	 * 初始化
	 * @param email
	 * @param mailServer
	 * @param mailServerPort
	 * @param mailFrom
	 * @param mailTo
	 * @param mailCc
	 * @param mailBcc
	 * @param userName
	 * @param userPwd
	 * @param charset
	 * @param subject
	 * @throws EmailException
	 */
	private static void init(Email email, String mailServer,
			Integer mailServerPort, String mailFrom, String[] mailTo,
			String[] mailCc, String[] mailBcc, String userName, String userPwd,
			String charset, String subject) throws EmailException {
		email.setHostName(mailServer);
		email.setSmtpPort(mailServerPort);
		email.setFrom(mailFrom);
		email.addTo(mailTo);
		if (mailCc != null)
			email.addCc(mailCc);
		if (mailBcc != null)
			email.addBcc(mailBcc);
		email.setAuthentication(userName, userPwd);
		email.setCharset(charset);
		email.setSubject(subject);
	}
	/**
	 * 添加附件
	 * @param email
	 * @param files
	 * @throws EmailException
	 */
	private static void attach(MultiPartEmail email, File[] files)
			throws EmailException {
		if (files == null) {
			return;
		}
		for (File file : files) {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(file.getAbsolutePath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setName(file.getName());
			email.attach(attachment);
		}
	}
	
	public static void main(String[] args) {
		try {
			EmailSendUtil.sendSimpleEmail("smtp.126.com", 25, "clbhii@126.com", new String[]{"chenglibin@souche.com"}, null, null,  "clbhii@126.com", "c563373558", "utf-8","提醒",  "提醒");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
