package com.cl.java.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Mapper注解的componentModel属性
 * componentModel 属性用于指定自动生成的接口实现类的组件类型。这个属性支持四个值：
		default: 这是默认的情况，mapstruct不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。
		cdi: the generated mapper is an application-scoped CDI bean and can be retrieved via @Inject
		spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
		jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取。
 * @author cl 2017年8月30日
 *  http://blog.csdn.net/paincupid/article/details/71247255
 */
@Mapper
public interface PeopleMapper {
	PeopleMapper MAPPER = Mappers.getMapper(PeopleMapper.class);
    void mapping(PeopleDO source, @MappingTarget PeopleDTO dest);
    
    PeopleDTO  mapping(PeopleDO source );
    @Mappings(
    		{
    			@Mapping(target = "name", source = "source.name"),
    			@Mapping(target = "age", source = "source1.age")

    		}
    )
    PeopleDTO  mapping(PeopleDO source, PeopleDO source1 );
    
    
}
