package com.example.web.aws.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.web.aws.service.MyService;

@Service
public class MyServiceImpl implements MyService {

	@Override
	public Date getCurrentDate() {
		return new Date();
	}

}
