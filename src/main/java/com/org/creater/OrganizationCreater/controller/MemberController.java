package com.org.creater.OrganizationCreater.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.creater.OrganizationCreater.config.EmailCfg;
import com.org.creater.OrganizationCreater.entity.Member;
import com.org.creater.OrganizationCreater.entity.MemberStatus;
import com.org.creater.OrganizationCreater.entity.Organization;
import com.org.creater.OrganizationCreater.service.MemberService;
import com.org.creater.OrganizationCreater.service.OrganizationService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private EmailCfg emailCfg;
	
	@Autowired
	private OrganizationService organizationService;
	
	public MemberController() {}
	
	//add new member
	@PostMapping("/member/addMember")
	public Member addMember(@RequestBody Member member, @HttpConstraint HttpServletRequest request) throws MessagingException {
		
		Member newMember=service.addMember(member);
		if(newMember==null) {
			return null;
		}
		
		Organization org=organizationService.getOrganizationById(member.getOrg().getOrgId());
		
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost(emailCfg.getHost());
		mailSender.setPort(emailCfg.getPort());
		mailSender.setUsername(emailCfg.getUsername());
		mailSender.setPassword(emailCfg.getPassword());
		
		//SimpleMailMessage mailMessage=new  SimpleMailMessage();
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper mailMessage=new MimeMessageHelper(message);
		
		mailMessage.setFrom("OrgCreator@w2p.com");
		mailMessage.setTo(member.getEmail());
		mailMessage.setSubject("Invitation to join "+org.getOrgName());
		
		String feedbackUrl=  request.getRequestURL().toString().replace("addMember", "accept/");
		feedbackUrl+= newMember.getMid();
		String body= "<p>Dear "+member.getName() +" </p> <br> <p>"
				+ "You have invted to join "+ org.getOrgName() 
				+" please confirm "+ "<br>" +"</P>"
				+"<h3>"+"<a href=\""+feedbackUrl+"\">Accept</a>" +"</h3>"
				+"<br>"+"<h3>"+"<a href=\""+feedbackUrl.replace("accept", "reject")+"\">Reject</a>" +"</h3>";
		mailMessage.setText(body,true);
		System.out.println(request.getServletPath()+"\n" + request.getRequestURL());
		//mailMessage.
		mailSender.send(message);
		
		
		return newMember;
	}
	
	//trigger by accept email invitation
	@GetMapping("/member/accept/{mid}")
	public Member acceptMemberInvitation(@PathVariable long mid) {
		
		return this.service.updateStatus(MemberStatus.ACCEPTED, mid);
		
	}
	
	//trigger by reject email invitation
	@GetMapping("/member/reject/{mid}")
	public Member rejectMemberInvitation(@PathVariable long mid) {
		
		return this.service.updateStatus(MemberStatus.REJECTED, mid);
		
	}
	//get all members of a org
	@GetMapping("/member/getAllMembers/{oid}")
	public List<Member> getAllMembersByOrganization(@PathVariable long oid){
		return this.service.getAllMembersByOid(oid);
	}

}
