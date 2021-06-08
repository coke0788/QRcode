package com.gd.qrtest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.qrtest.vo.QRInputForm;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class QRService {
	public Map<String, Object> getForm(QRInputForm qrInputForm){
		String name = qrInputForm.getName();
		String gender = qrInputForm.getGender();
		int age = qrInputForm.getAge();
		String phone = qrInputForm.getPhone();
		Map<String, Object> map = new HashMap<>();
		map.put("gender", gender);
		map.put("name", name);
		map.put("age", age);
		map.put("phone", phone);
		log.debug("===================이름:"+map.get("name"));
		return map;
	}
}
