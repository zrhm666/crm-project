package com.crm.workbench.service.impl;

import com.crm.commons.constants.Constants;
import com.crm.commons.utils.DateUtil;
import com.crm.commons.utils.UUIDUtil;
import com.crm.setting.pojo.User;
import com.crm.workbench.mapper.*;
import com.crm.workbench.pojo.*;
import com.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: zr
 * Date: 2023/1/18 16:33
 * Description:
 */
@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    ClueMapper clueMapper;
    @Autowired
    TranMapper tranMapper;
    @Autowired
    TranRemarkMapper tranRemarkMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ContactsMapper contactsMapper;
    @Autowired
    ClueRemarkMapper clueRemarkMapper;
    @Autowired
    ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int insertClue(Clue clue) {
        return clueMapper.insertSelective(clue);
    }

    @Override
    public PageInfo<Clue> queryAllClue(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Clue> clueList = clueMapper.selectAllClue();
        PageInfo<Clue> cluePageInfo = new PageInfo<Clue>(clueList);
        return cluePageInfo;
    }

    public Clue queryClueById(String id){
        return clueMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void clueConvert(Map<String,Object> map) {
        User user = (User) map.get(Constants.SESSION_USER);
        Clue clue = clueMapper.selectClueById((String) map.get("clueId"));

        Customer customer = new Customer();
        customer.setAddress(clue.getAddress());
        customer.setContactSummary(clue.getContactSummary());
        customer.setId(UUIDUtil.getUUID());
        customer.setDescription(clue.getDescription());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtil.formatDateTime(new Date()));
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setOwner(user.getId());
        customer.setName(clue.getCompany());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        customerMapper.insertSelective(customer);

        Contacts contacts = new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setDescription(clue.getDescription());
        contacts.setId(UUIDUtil.getUUID());
        contacts.setCreateTime(DateUtil.formatDateTime(new Date()));
        contacts.setCreateBy(user.getId());
        contacts.setEmail(clue.getEmail());
        contacts.setJob(clue.getJob());
        contacts.setFullname(clue.getFullname());
        contacts.setOwner(user.getId());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setSource(clue.getSource());
        contacts.setMphone(clue.getMphone());
        contacts.setCustomerId(customer.getId());
        contactsMapper.insertSelective(contacts);

//      转俩备注
        List<ClueRemark> clueRemarkList= clueRemarkMapper.selectByClueId(clue.getId());
        List<CustomerRemark> customerRemarkList = new ArrayList<CustomerRemark>();
        List<ContactsRemark> contactsRemarkList = new ArrayList<ContactsRemark>();
        if (clueRemarkList.size()>0 && clueRemarkList!=null) {
            for (ClueRemark clueRemark:clueRemarkList) {
                CustomerRemark customerRemark = new CustomerRemark();
                customerRemark.setId(UUIDUtil.getUUID());
                customerRemark.setCustomerId(customer.getId());
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(clueRemark.getCreateTime());
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditTime(clueRemark.getEditTime());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemarkList.add(customerRemark);

                ContactsRemark contactsRemark = new ContactsRemark();
                contactsRemark.setId(UUIDUtil.getUUID());
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemarkList.add(contactsRemark);
            }
            customerRemarkMapper.insertCustomerRemarks(customerRemarkList);
            contactsRemarkMapper.insertContactsRemarks(contactsRemarkList);
        }

//      转线索-活动关联到客户-活动关联
        List<String> actIds = clueActivityRelationMapper.selectActIdsByClueId(clue.getId());
        List<ContactsActivityRelation> carList = new ArrayList<ContactsActivityRelation>();
        if (actIds!=null&&actIds.size()>0) {
            for (String actId:actIds){
                ContactsActivityRelation car = new ContactsActivityRelation();
                car.setId(UUIDUtil.getUUID());
                car.setContactsId(contacts.getId());
                car.setActivityId(actId);
                carList.add(car);
            }
            contactsActivityRelationMapper.insertConActRelations(carList);
        }

//      判断是否需要创建交易，有则创建
        if (true==(Boolean) map.get("isCreateTran")){
            Tran tran = new Tran();
            tran.setId(UUIDUtil.getUUID());
            tran.setMoney((String) map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setStage((String) map.get("stage"));
            tran.setActivityId((String) map.get("activityId"));
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtil.formatDateTime(new Date()));
            tran.setContactsId(contacts.getId());
            tran.setCustomerId(customer.getId());
            tran.setOwner(user.getId());
            tranMapper.insertSelective(tran);

            //转交易备注
            List<TranRemark> tranRemarkList = new ArrayList<TranRemark>();
            if (clueRemarkList.size()>0 && clueRemarkList!=null) {
                TranRemark tranRemark = null;
                for (ClueRemark clueRemark:clueRemarkList) {
                    tranRemark = new TranRemark();
                    tranRemark.setId(UUIDUtil.getUUID());
                    tranRemark.setTranId(customer.getId());
                    tranRemark.setCreateBy(clueRemark.getCreateBy());
                    tranRemark.setCreateTime(clueRemark.getCreateTime());
                    tranRemark.setEditBy(clueRemark.getEditBy());
                    tranRemark.setEditTime(clueRemark.getEditTime());
                    tranRemark.setEditFlag(clueRemark.getEditFlag());
                    tranRemark.setNoteContent(clueRemark.getNoteContent());
                    tranRemarkList.add(tranRemark);
                }
                tranRemarkMapper.insertTranRemarks(tranRemarkList);
            }

            //删除掉线索下所有备注(外键先删)
            clueRemarkMapper.deleteClueRemarksByClueId(clue.getId());
            //删除掉线索和活动的关联(外键先删)
            clueActivityRelationMapper.deleteClueActRelationsByClueId(clue.getId());
            //删除掉线索
            clueMapper.deleteByPrimaryKey(clue.getId());
        }
    }
}
