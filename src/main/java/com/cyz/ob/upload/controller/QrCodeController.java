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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/qrcode")
public class QrCodeController {
	
	@Value("own.qrcode.loginPath")
	private String loginPath;

	/*
	 * @GetMapping(value="/loginCode", produces = MediaType.IMAGE_JPEG_VALUE) public
	 * BufferedImage loginQrCode(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException {
	 * 
	 * 
	 * String content = "";
	 * 
	 * return QrCodeUtil.getBufferedImage("123", 100, "D:/头像.jpg");
	 * 
	 * }
	 */
}
