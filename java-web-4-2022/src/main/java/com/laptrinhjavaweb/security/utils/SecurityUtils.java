package com.laptrinhjavaweb.security.utils;

import com.laptrinhjavaweb.dto.MyUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    // muốn lấy thông tin gì từ MyUserDetail thì chỉ cần SecurityUtils.getPrincipal.getFullName() vậy thôi
    public static MyUserDetail getPrincipal() {
        return (MyUserDetail) (SecurityContextHolder
                .getContext()).getAuthentication().getPrincipal();
    }

    // cái này gọi tương tự như trên , dùng để getRole() của thằng đó ra ok?
    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}
