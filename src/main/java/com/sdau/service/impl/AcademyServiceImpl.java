package com.sdau.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdau.entity.Academy;
import com.sdau.mapper.AcademyMapper;
import com.sdau.service.AcademyService;
import org.springframework.stereotype.Service;

@Service
public class AcademyServiceImpl extends ServiceImpl<AcademyMapper, Academy>
 implements AcademyService {
}
