package cn.tohuangshuai.service.impl;

import cn.tohuangshuai.common.util.PagedResult;
import cn.tohuangshuai.dao.mapper.AdviceMapper;
import cn.tohuangshuai.pojo.domain.Advice;
import cn.tohuangshuai.service.AdviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    /**
     *  保存通知
     * @param advice
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String save(Advice advice) {
        adviceMapper.insertSelective(advice);
        return advice.getId();
    }

    /**
     * 分页查询通知 tch
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult getAllAdvice(Integer page, Integer pageSize, String classId) {

        //开启分页
        PageHelper.startPage(page,pageSize);
        List<Advice> list = adviceMapper.queryAllAdvice(classId);
        PageInfo<Advice> listInfo = new PageInfo<>(list);
        PagedResult result = new PagedResult();
        result.setPage(page); //当前页
        result.setRecord(listInfo.getTotal()); //总条数
        result.setRows(list); //每页数据
        result.setTotal(listInfo.getPages());  //总页数
        return result;

    }
}
