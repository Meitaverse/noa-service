package me.bitsoul.noa.service;

import me.bitsoul.noa.dao.IdSequenceDAO;
import me.bitsoul.noa.entry.IdSequenceDO;
import me.bitsoul.noa.enums.IdSceneEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lxbang
 * @create 2022/12/2 3:30 下午
 */
@Service
public class IdService {

    @Autowired
    private IdSequenceDAO idSequenceDAO;

    /**
     * 获取唯一id
     * @param sceneEnum
     * @return
     */
    public Long getNextId(IdSceneEnum sceneEnum){
        Long nextId;
        do {
            IdSequenceDO sequence = getWithCreate(sceneEnum);
            nextId = idSequenceDAO.getSerialNo(sceneEnum.getVal(), sequence.getVersion(), 1);
        } while (nextId == null);
        return nextId;
    }

    /**
     * 获取指定场景下的序号记录（自动创建）
     * @param sceneEnum
     * @return
     */
    public IdSequenceDO getWithCreate(IdSceneEnum sceneEnum){
        String scene = sceneEnum.getVal();
        IdSequenceDO sequence = idSequenceDAO.findByScene(scene);
        if (Objects.isNull(sequence)){
            final long initId = 9999;
            IdSequenceDO idSequenceDO = new IdSequenceDO();
            idSequenceDO.setScene(scene);
            idSequenceDO.setSerialNo(initId);
            sequence = idSequenceDAO.save(idSequenceDO);
        }
        return sequence;
    }

}
