/**
 * Copyright (c) 2016-2019 蜂鸟开源 All rights reserved.
 *
 * https://www.hummingbird.io
 *
 * 版权所有，侵权必究！
 */

package io.hummingbird.controller;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hummingbird.entity.UserEntity;
import io.hummingbird.form.RegisterForm;
import io.hummingbird.service.UserService;
import io.hummingbird.common.utils.R;
import io.hummingbird.common.validator.ValidatorUtils;

/**
 * 注册接口
 *
 * @author Top
 */
@RestController
@RequestMapping("/api")
// @Api(tags="注册接口")
public class ApiRegisterController {
	@Autowired
	private UserService userService;

	@PostMapping("register")
	// @ApiOperation("注册")
	public R register(@RequestBody RegisterForm form) {
		// 表单校验
		ValidatorUtils.validateEntity(form);

		UserEntity user = new UserEntity();
		user.setMobile(form.getMobile());
		user.setUsername(form.getMobile());
		user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
		user.setCreateTime(new Date());
		userService.save(user);

		return R.ok();
	}
}
