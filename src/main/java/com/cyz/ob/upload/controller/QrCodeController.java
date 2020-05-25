package com.cyz.ob.upload.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.util.QrCodeUtil;



@RestController
@RequestMapping(value = "/api/qrcode")
public class QrCodeController {
	
	@Value("${own.qrcode.loginPath}")
	private String loginPath;

	@GetMapping(value="/{id}/loginCode", produces = MediaType.IMAGE_JPEG_VALUE) 
	public BufferedImage loginQrCode(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id")String id) throws IOException {
	 String prefix = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/api"));
	 return QrCodeUtil.getBufferedImage(prefix + loginPath + "/" + id, 150, null);
	}

}
