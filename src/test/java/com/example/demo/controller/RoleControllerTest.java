package com.example.demo.controller;

import com.example.demo.entity.AvailableRoleEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.exceptions.ExceptionHandlerClass;
import com.example.demo.exceptions.NoCustomerException;
import com.example.demo.service.IRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    private static final ObjectMapper OM = new ObjectMapper();

    private MockMvc mockMvc;

    @Mock
    private IRoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    public void listAllTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/role")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void addUserTest() throws Exception {

        AvailableRoleEntity availableRoleEntity = new AvailableRoleEntity();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/role")
                 .contentType(MediaType.APPLICATION_JSON)
                .content(OM.writeValueAsString(availableRoleEntity))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void updateUserTest() throws Exception {

        AvailableRoleEntity availableRoleEntity = new AvailableRoleEntity();

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(availableRoleEntity))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }


    @Test
    public void calculateExchangeRate_throwException() {
        AvailableRoleEntity roleEntity = new AvailableRoleEntity();
        roleEntity.setRoleId(1);
        roleEntity.setRole("ROLE_ADMIN");
        assertThrows(NoCustomerException.class, () ->
                roleController.deleteRole(roleEntity));

    }
}
