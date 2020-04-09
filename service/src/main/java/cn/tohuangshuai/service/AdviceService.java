package cn.tohuangshuai.service;

import cn.tohuangshuai.common.util.PagedResult;
import cn.tohuangshuai.pojo.domain.Advice;
import org.springframework.stereotype.Service;

@Service
public interface AdviceService {

    String save(Advice advice);

    PagedResult getAllAdvice(Integer page, Integer pageSize, String advice);

    void updateUserFace(String id, String imageUrl);
}
