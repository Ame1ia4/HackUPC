package com.registration.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return "Username already exists";
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return "Email already exists";
        }

        // Create the User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        AddressDTO addressDTO = userDTO.getAddress();
        if (addressDTO != null) {
            Address address = new Address();
            address.setAddressLine1(addressDTO.getAddressLine1());
            address.setAddressLine2(addressDTO.getAddressLine2());
            address.setCity(addressDTO.getCity());
            address.setPostcode(addressDTO.getPostcode());
            address.setCountry(addressDTO.getCountry());

            user.setAddress(address);
        }

        userRepository.save(user);
        return "User registered successfully";
    }

    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
