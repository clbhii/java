/*
 * 系统名称: eden 1.0
 * 模块名称: eden.common.email
 * 类 名 称: ImageHtmlEmailSend.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.email;

import java.io.File;
import java.net.URL;

import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-5-30 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class ImageHtmlEmailSend extends AbstractEmailSend{

	private URL dataSourceUrl;
	
	protected ImageHtmlEmail email;
	
	public ImageHtmlEmailSend(String mailServer, Integer mailServerPort,
			String mailFrom, String[] mailTo, String[] mailCc,
			String[] mailBcc, String userName, String userPwd, String charset,
			String subject, String content, File[] attachs,URL dataSourceUrl) {
		super(mailServer, mailServerPort, mailFrom, mailTo, mailCc, mailBcc, userName,
				userPwd, charset, subject, content, attachs);
		this.dataSourceUrl=dataSourceUrl;
	}

	public void send() throws Exception {
		try {
			ImageHtmlEmail email = new ImageHtmlEmail();
			init(email);
			email.setDataSourceResolver(new DataSourceUrlResolver(dataSourceUrl, true));
			email.setHtmlMsg(content);
			attach(email);
			email.send();
		} catch (org.apache.commons.mail.EmailException e) {
			throw new Exception("发送html格式邮件失败", e);
		}
		
	}

	
	
}
