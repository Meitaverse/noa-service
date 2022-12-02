package me.bitsoul.noa.service;

import me.bitsoul.noa.exception.BusinessException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * @author lxbang
 * @create 2022/12/2 4:26 下午
 */
public interface IService<DO,DTO> {

    /**
     * 转换成dto
     * @param entry
     * @return
     */
    default DTO toDTO(DO entry) {
        try {
            if (Objects.isNull(entry)){
                return null;
            }
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            Class<DTO> dtoClass = (Class<DTO>) genericSuperclass.getActualTypeArguments()[1];
            DTO dto = dtoClass.newInstance();
            BeanUtils.copyProperties(entry,dto);
            return dto;
        } catch (Exception e) {
            throw new BusinessException(500,"转换成dto失败！");
        }
    }

}
