package com.smartcampus.security;

import com.smartcampus.model.entity.User;
import com.smartcampus.model.enums.Provider;
import com.smartcampus.model.enums.Role;
import com.smartcampus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        
        // Extract info from Google
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String sub = oauth2User.getAttribute("sub");
        String picture = oauth2User.getAttribute("picture");

        // TODO: Ensure user exists in DB, or auto-register logic
        // For scaffold, we pretend to fetch or create
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            User newUser = User.builder()
                .email(email)
                .name(name)
                .provider(Provider.GOOGLE)
                .providerId(sub)
                .profilePicture(picture)
                .role(Role.USER) // Default role
                .build();
            userRepository.save(newUser);
        }

        return oauth2User;
    }
}
