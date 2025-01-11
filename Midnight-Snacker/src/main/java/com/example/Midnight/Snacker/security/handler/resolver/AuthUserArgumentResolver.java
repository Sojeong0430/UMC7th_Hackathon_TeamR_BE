package com.example.Midnight.Snacker.security.handler.resolver;

import com.example.Midnight.Snacker.apiPayload.exception.AuthException;
import com.example.Midnight.Snacker.security.principal.PrincipalDetails;
import com.example.Midnight.Snacker.service.MemberService.MemberService;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.Midnight.Snacker.apiPayload.code.status.ErrorStatus;
import com.example.Midnight.Snacker.apiPayload.exception.GeneralException;
import com.example.Midnight.Snacker.domain.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Member.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws AuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = null;
        if (authentication != null) {
            // 로그인하지 않은 익명 사용자라면 null 반환
            if (authentication.getName().equals("anonymousUser")) {
                return null;
            }
            principal = authentication.getPrincipal();
        }
        if (principal == null || principal.getClass() == String.class) {
            throw new AuthException(ErrorStatus.USER_NOT_FOUND);
        }

        if (principal instanceof PrincipalDetails) {
            PrincipalDetails userDetails = (PrincipalDetails) principal;
            Long userId = userDetails.getId();
            return memberService.findMemberById(userId);
        }
        return null;
    }
}
