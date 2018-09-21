package com.cl.java.util.beancopy;

/**
 * 
 * @author cl 2018年9月11日
 *
 */
public interface BeanCopyFacade<S,T> {

	   /**
     * bean copy.
     *
     * @param sourceBean source bean
     * @param targetBean target bean
     * @throws Exception root exception
     */
    void copyBean(S sourceBean, T targetBean) throws Exception;
}
