package com.microservices.user;

import com.microservices.core.exceptions.ObjectNotFoundException;
import com.microservices.core.response.PageService;
import com.microservices.user.role.RoleService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleService roleService;

    public UserDTO findById(Long id) {
        return findUserById(id).map(UserDTO::new).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows({DataIntegrityViolationException.class})
    public User saveCreator(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setRole(roleService.findByName("CREATOR"));
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows({DataIntegrityViolationException.class})
    public User save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setRole(roleService.findByName("USER"));
        return userRepository.save(user);
    }

    public Page<UserDTO> findAll(Integer page, Integer size, String orderBy, String filter, String param) {
        PageRequest pageRequest = PageService.of(page, size, filter, orderBy, new User());
        Page<UserDTO> userDTO = userRepository.findAll(param, pageRequest).map(UserDTO::new);
        return userDTO.getContent().isEmpty() ? null : userDTO;
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        return findUserById(id)
                .map(user -> {
                    user.setName(userDTO.getName());
                    user.setUsername(userDTO.getName());
                    userRepository.save(user);
                    return new UserDTO(user);
                }).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        User user = findUserById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        user.softDelete();
        userRepository.save(user);
    }

}
