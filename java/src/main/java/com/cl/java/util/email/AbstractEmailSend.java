/*
 * 系统名称: eden 1.0
 * 模块名称: eden.common.email
 * 类 名 称: AbstractEmailSend.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.email;

import java.io.File;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;


/**
 * 功能说明: <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-5-29 <br>
 * 审核人员: <br>
 * 相关文档: <br>
 * 修改记录: <br>
 * 修改日期 修改人员 修改说明 <br>
 * ======== ====== ============================================ <br>
 * 
 */
public abstract class AbstractEmailSend implements EmailSend {
	/**
	 * 邮件服务器
	 */
	protected String mailServer;
	/**
	 * 邮件服务器地址
	 */
	protected Integer mailServerPort;
	/**
	 * 发件人
	 */
	protected String mailFrom;
	/**
	 * 收件人
	 */
	protected String[] mailTo;
	/**
	 * 抄送人
	 */
	protected String[] mailCc;
	/**
	 * 密送人
	 */
	protected String[] mailBcc;
	/**
	 * 用户名
	 */
	protected String userName;
	/**
	 * 用户密码
	 */
	protected String userPwd;
	/**
	 * 编码
	 */
	protected String charset;
	/**
	 * 主题
	 */
	protected String subject;
	/**
	 * 正文
	 */
	protected String content;
	/**
	 * 附件
	 */
	protected File[] attachs;

	
	public AbstractEmailSend(String mailServer, Integer mailServerPort,
			String mailFrom, String[] mailTo, String[] mailCc,
			String[] mailBcc, String userName, String userPwd, String charset,
			String subject, String content, File[] attachs) {
		super();
		this.mailServer = mailServer;
		this.mailServerPort = mailServerPort;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.mailCc = mailCc;
		this.mailBcc = mailBcc;
		this.userName = userName;
		this.userPwd = userPwd;
		this.charset = charset;
		this.subject = subject;
		this.content = content;
		this.attachs = attachs;
	}

	/**
	 * 初始化
	 * 
	 * @throws EmailException
	 */
	protected void init(Email email) throws EmailException {
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
	protected  void attach(MultiPartEmail email)
			throws EmailException {
		if (attachs == null) {
			return;
		}
		for (File file : attachs) {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(file.getAbsolutePath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setName(file.getName());
			email.attach(attachment);
		}
	}

	
	
}
