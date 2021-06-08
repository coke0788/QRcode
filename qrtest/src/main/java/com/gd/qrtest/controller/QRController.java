package com.gd.qrtest.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.qrtest.service.QRService;
import com.gd.qrtest.vo.QRInputForm;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Controller
public class QRController {
	@Autowired QRService qrService;
	@GetMapping("/qrForm")
	public String getQRForm() {
		return "qrForm";
	}
	@PostMapping("/qrForm")
	public String getForm(Model model, QRInputForm qrInputForm) {
		Map<String, Object> map = qrService.getForm(qrInputForm);
		String name = (String) map.get("name");
		String gender = (String) map.get("gender");
		int age = (Integer) map.get("age");
		String phone = (String) map.get("phone");
		String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		try {
			//글자 인코딩. 한글 들어가는 것들은 모두 인코딩하기.
			name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer contents = new StringBuffer();
		contents.append(name).append(",").append(gender).append(",").append(age).append(",").append(phone).append(",").append(osName).append(",").append(osVersion).append(",");
		QRCodeWriter qrWriter = new QRCodeWriter();
		log.debug("=================QR 정보 :"+contents);
		BitMatrix matrix = null;
		try {
			matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE , 150, 150);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		log.debug("=================QR 정보 matrix :"+matrix);
		MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, config);
		
		//프로젝트 경로 (새로 파일 암거나 만들어서 경로 찾기)
		File temp = new File("");
		String path = temp.getAbsolutePath();
		String prename = UUID.randomUUID().toString().replace("-", ""); //중복되지 않는 랜덤이름을. -는 공백으로 변경해서.
		String fileName = prename+".png";
        try {
			ImageIO.write(qrImage, "png", new File(path+"\\src\\main\\webapp\\img\\"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
        log.debug("=================QR 정보2 :"+qrImage);
		return "redirect:/completeForm?fileName="+fileName;
	}
	
	@GetMapping("/completeForm")
	public String getCompleteForm(Model model, @RequestParam(value="fileName", required=false) String fileName) {
		model.addAttribute("fileName", fileName);
		return "completeForm";
	}
}
