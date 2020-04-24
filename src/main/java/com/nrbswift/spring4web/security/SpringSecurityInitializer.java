package com.nrbswift.spring4web.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    /**
     * DOC
     *
     * The last step is we need to map the springSecurityFilterChain. We can easily do this by extending AbstractSecurityWebApplicationInitializer
     * and optionally overriding methods to customize the mapping.
     *
     * The most basic example below accepts the default mapping and adds springSecurityFilterChain with the following characteristics:
     *
     * springSecurityFilterChain is mapped to “/*”
     * springSecurityFilterChain uses the dispatch types of ERROR and REQUEST
     * The springSecurityFilterChain mapping is inserted before any servlet Filter mappings that have already been configured
     *
     * */
}
