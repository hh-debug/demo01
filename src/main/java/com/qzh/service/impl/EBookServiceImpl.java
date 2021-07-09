package com.qzh.service.impl;

import com.qzh.domain.Ebook;
import com.qzh.domain.EbookExample;
import com.qzh.mapper.EbookMapper;
import com.qzh.req.EbookReq;
import com.qzh.resp.EbookResp;
import com.qzh.service.EBookService;
import com.qzh.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @packageName:com.qzh.service
 * @ClassName:EBookServiceImpl
 * @date:2021/7/913:37
 */
@Service
public class EBookServiceImpl implements EBookService {

    @Autowired
    private EbookMapper ebookMapper;

    @Override
    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }

    @Override
    public List<EbookResp> likeNameList(EbookReq ebookReq) {
        EbookExample ebookExample = new EbookExample();//创建示例
        EbookExample.Criteria criteria = ebookExample.createCriteria();//创建where条件
        criteria.andNameLike("%" + ebookReq.getName() + "%");//添加模糊查询条件

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);//根据条件查询所有的ebook

        //不应该响应全部的数据库字段 有些是敏感字段
        List<EbookResp> ebookRespList = new ArrayList<>();
        System.out.println(ebookList);

//        for (Ebook ebook : ebookList) {
////            EbookResp ebookResp = new EbookResp();
//            //注意下面源跟目标 源是每一个循环的ebook 目标是对应的实体
//            BeanUtils.copyProperties(ebook,ebookResp);
//            ebookRespList.add(ebookResp);
//
//            //对象的复制
//            EbookResp resp = CopyUtil.copy(ebook, EbookResp.class);
//        }
//        System.out.println(ebookRespList);

//        列表的复制
        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
        return ebookRespList;
    }
}