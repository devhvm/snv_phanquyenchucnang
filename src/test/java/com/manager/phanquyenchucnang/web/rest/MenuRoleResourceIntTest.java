package com.manager.phanquyenchucnang.web.rest;

import com.manager.phanquyenchucnang.PhanquyenchucnangApp;

import com.manager.phanquyenchucnang.domain.MenuRole;
import com.manager.phanquyenchucnang.repository.MenuRoleRepository;
import com.manager.phanquyenchucnang.service.MenuRoleService;
import com.manager.phanquyenchucnang.service.dto.MenuRoleDTO;
import com.manager.phanquyenchucnang.service.mapper.MenuRoleMapper;
import com.manager.phanquyenchucnang.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.manager.phanquyenchucnang.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MenuRoleResource REST controller.
 *
 * @see MenuRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhanquyenchucnangApp.class)
public class MenuRoleResourceIntTest {

    private static final String DEFAULT_MENU_ROLE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ROLE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MenuRoleRepository menuRoleRepository;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Autowired
    private MenuRoleService menuRoleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMenuRoleMockMvc;

    private MenuRole menuRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuRoleResource menuRoleResource = new MenuRoleResource(menuRoleService);
        this.restMenuRoleMockMvc = MockMvcBuilders.standaloneSetup(menuRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuRole createEntity(EntityManager em) {
        MenuRole menuRole = new MenuRole()
            .menuRoleCode(DEFAULT_MENU_ROLE_CODE)
            .role(DEFAULT_ROLE)
            .name(DEFAULT_NAME);
        return menuRole;
    }

    @Before
    public void initTest() {
        menuRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuRole() throws Exception {
        int databaseSizeBeforeCreate = menuRoleRepository.findAll().size();

        // Create the MenuRole
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(menuRole);
        restMenuRoleMockMvc.perform(post("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the MenuRole in the database
        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeCreate + 1);
        MenuRole testMenuRole = menuRoleList.get(menuRoleList.size() - 1);
        assertThat(testMenuRole.getMenuRoleCode()).isEqualTo(DEFAULT_MENU_ROLE_CODE);
        assertThat(testMenuRole.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMenuRole.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMenuRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuRoleRepository.findAll().size();

        // Create the MenuRole with an existing ID
        menuRole.setId(1L);
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(menuRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuRoleMockMvc.perform(post("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuRole in the database
        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMenuRoleCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = menuRoleRepository.findAll().size();
        // set the field null
        menuRole.setMenuRoleCode(null);

        // Create the MenuRole, which fails.
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(menuRole);

        restMenuRoleMockMvc.perform(post("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isBadRequest());

        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = menuRoleRepository.findAll().size();
        // set the field null
        menuRole.setRole(null);

        // Create the MenuRole, which fails.
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(menuRole);

        restMenuRoleMockMvc.perform(post("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isBadRequest());

        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = menuRoleRepository.findAll().size();
        // set the field null
        menuRole.setName(null);

        // Create the MenuRole, which fails.
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(menuRole);

        restMenuRoleMockMvc.perform(post("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isBadRequest());

        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMenuRoles() throws Exception {
        // Initialize the database
        menuRoleRepository.saveAndFlush(menuRole);

        // Get all the menuRoleList
        restMenuRoleMockMvc.perform(get("/api/menu-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuRoleCode").value(hasItem(DEFAULT_MENU_ROLE_CODE.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMenuRole() throws Exception {
        // Initialize the database
        menuRoleRepository.saveAndFlush(menuRole);

        // Get the menuRole
        restMenuRoleMockMvc.perform(get("/api/menu-roles/{id}", menuRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menuRole.getId().intValue()))
            .andExpect(jsonPath("$.menuRoleCode").value(DEFAULT_MENU_ROLE_CODE.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMenuRole() throws Exception {
        // Get the menuRole
        restMenuRoleMockMvc.perform(get("/api/menu-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuRole() throws Exception {
        // Initialize the database
        menuRoleRepository.saveAndFlush(menuRole);

        int databaseSizeBeforeUpdate = menuRoleRepository.findAll().size();

        // Update the menuRole
        MenuRole updatedMenuRole = menuRoleRepository.findById(menuRole.getId()).get();
        // Disconnect from session so that the updates on updatedMenuRole are not directly saved in db
        em.detach(updatedMenuRole);
        updatedMenuRole
            .menuRoleCode(UPDATED_MENU_ROLE_CODE)
            .role(UPDATED_ROLE)
            .name(UPDATED_NAME);
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(updatedMenuRole);

        restMenuRoleMockMvc.perform(put("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isOk());

        // Validate the MenuRole in the database
        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeUpdate);
        MenuRole testMenuRole = menuRoleList.get(menuRoleList.size() - 1);
        assertThat(testMenuRole.getMenuRoleCode()).isEqualTo(UPDATED_MENU_ROLE_CODE);
        assertThat(testMenuRole.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testMenuRole.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuRole() throws Exception {
        int databaseSizeBeforeUpdate = menuRoleRepository.findAll().size();

        // Create the MenuRole
        MenuRoleDTO menuRoleDTO = menuRoleMapper.toDto(menuRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuRoleMockMvc.perform(put("/api/menu-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuRole in the database
        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuRole() throws Exception {
        // Initialize the database
        menuRoleRepository.saveAndFlush(menuRole);

        int databaseSizeBeforeDelete = menuRoleRepository.findAll().size();

        // Delete the menuRole
        restMenuRoleMockMvc.perform(delete("/api/menu-roles/{id}", menuRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MenuRole> menuRoleList = menuRoleRepository.findAll();
        assertThat(menuRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuRole.class);
        MenuRole menuRole1 = new MenuRole();
        menuRole1.setId(1L);
        MenuRole menuRole2 = new MenuRole();
        menuRole2.setId(menuRole1.getId());
        assertThat(menuRole1).isEqualTo(menuRole2);
        menuRole2.setId(2L);
        assertThat(menuRole1).isNotEqualTo(menuRole2);
        menuRole1.setId(null);
        assertThat(menuRole1).isNotEqualTo(menuRole2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuRoleDTO.class);
        MenuRoleDTO menuRoleDTO1 = new MenuRoleDTO();
        menuRoleDTO1.setId(1L);
        MenuRoleDTO menuRoleDTO2 = new MenuRoleDTO();
        assertThat(menuRoleDTO1).isNotEqualTo(menuRoleDTO2);
        menuRoleDTO2.setId(menuRoleDTO1.getId());
        assertThat(menuRoleDTO1).isEqualTo(menuRoleDTO2);
        menuRoleDTO2.setId(2L);
        assertThat(menuRoleDTO1).isNotEqualTo(menuRoleDTO2);
        menuRoleDTO1.setId(null);
        assertThat(menuRoleDTO1).isNotEqualTo(menuRoleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(menuRoleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(menuRoleMapper.fromId(null)).isNull();
    }
}
