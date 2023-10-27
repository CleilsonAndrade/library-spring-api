package br.cleilsonandrade.libraryspringapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.cleilsonandrade.libraryspringapi.filter.FilterToken;

@Configuration
public class JWTConfig {
  @Bean
  public FilterRegistrationBean<FilterToken> filterRoute() {
    FilterRegistrationBean<FilterToken> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new FilterToken());
    filterRegistrationBean.addUrlPatterns("/auth/users/*");
    return filterRegistrationBean;
  }
}
