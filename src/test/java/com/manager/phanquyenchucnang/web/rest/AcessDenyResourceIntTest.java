package com.manager.phanquyenchucnang.web.rest;

import com.manager.phanquyenchucnang.PhanquyenchucnangApp;

import com.manager.phanquyenchucnang.domain.AcessDeny;
import com.manager.phanquyenchucnang.repository.AcessDenyRepository;
import com.manager.phanquyenchucnang.service.AcessDenyService;
import com.manager.phanquyenchucnang.service.dto.AcessDenyDTO;
import com.manager.phanquyenchucnang.service.mapper.AcessDenyMapper;
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
 * Test class for the AcessDenyResource REST controller.
 *
 * @see AcessDenyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhanquyenchucnangApp.class)
public class AcessDenyResourceIntTest {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private AcessDenyRepository acessDenyRepository;

    @Autowired
    private AcessDenyMapper acessDenyMapper;

    @Autowired
    private AcessDenyService acessDenyService;

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

    private MockMvc restAcessDenyMockMvc;

    private AcessDeny acessDeny;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcessDenyResource acessDenyResource = new AcessDenyResource(acessDenyService);
        this.restAcessDenyMockMvc = MockMvcBuilders.standaloneSetup(acessDenyResource)
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
    public static AcessDeny createEntity(EntityManager em) {
        AcessDeny acessDeny = new AcessDeny()
            .userId(DEFAULT_USER_ID);
        return acessDeny;
    }

    @Before
    public void initTest() {
        acessDeny = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcessDeny() throws Exception {
        int databaseSizeBeforeCreate = acessDenyRepository.findAll().size();

        // Create the AcessDeny
        AcessDenyDTO acessDenyDTO = acessDenyMapper.toDto(acessDeny);
        restAcessDenyMockMvc.perform(post("/api/acess-denies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acessDenyDTO)))
            .andExpect(status().isCreated());

        // Validate the AcessDeny in the database
        List<AcessDeny> acessDenyList = acessDenyRepository.findAll();
        assertThat(acessDenyList).hasSize(databaseSizeBeforeCreate + 1);
        AcessDeny testAcessDeny = acessDenyList.get(acessDenyList.size() - 1);
        assertThat(testAcessDeny.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createAcessDenyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acessDenyRepository.findAll().size();

        // Create the AcessDeny with an existing ID
        acessDeny.setId(1L);
        AcessDenyDTO acessDenyDTO = acessDenyMapper.toDto(acessDeny);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcessDenyMockMvc.perform(post("/api/acess-denies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acessDenyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcessDeny in the database
        List<AcessDeny> acessDenyList = acessDenyRepository.findAll();
        assertThat(acessDenyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = acessDenyRepository.findAll().size();
        // set the field null
        acessDeny.setUserId(null);

        // Create the AcessDeny, which fails.
        AcessDenyDTO acessDenyDTO = acessDenyMapper.toDto(acessDeny);

        restAcessDenyMockMvc.perform(post("/api/acess-denies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acessDenyDTO)))
            .andExpect(status().isBadRequest());

        List<AcessDeny> acessDenyList = acessDenyRepository.findAll();
        assertThat(acessDenyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcessDenies() throws Exception {
        // Initialize the database
        acessDenyRepository.saveAndFlush(acessDeny);

        // Get all the acessDenyList
        restAcessDenyMockMvc.perform(get("/api/acess-denies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acessDeny.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getAcessDeny() throws Exception {
        // Initialize the database
        acessDenyRepository.saveAndFlush(acessDeny);

        // Get the acessDeny
        restAcessDenyMockMvc.perform(get("/api/acess-denies/{id}", acessDeny.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acessDeny.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAcessDeny() throws Exception {
        // Get the acessDeny
        restAcessDenyMockMvc.perform(get("/api/acess-denies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcessDeny() throws Exception {
        // Initialize the database
        acessDenyRepository.saveAndFlush(acessDeny);

        int databaseSizeBeforeUpdate = acessDenyRepository.findAll().size();

        // Update the acessDeny
        AcessDeny updatedAcessDeny = acessDenyRepository.findById(acessDeny.getId()).get();
        // Disconnect from session so that the updates on updatedAcessDeny are not directly saved in db
        em.detach(updatedAcessDeny);
        updatedAcessDeny
            .userId(UPDATED_USER_ID);
        AcessDenyDTO acessDenyDTO = acessDenyMapper.toDto(updatedAcessDeny);

        restAcessDenyMockMvc.perform(put("/api/acess-denies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acessDenyDTO)))
            .andExpect(status().isOk());

        // Validate the AcessDeny in the database
        List<AcessDeny> acessDenyList = acessDenyRepository.findAll();
        assertThat(acessDenyList).hasSize(databaseSizeBeforeUpdate);
        AcessDeny testAcessDeny = acessDenyList.get(acessDenyList.size() - 1);
        assertThat(testAcessDeny.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAcessDeny() throws Exception {
        int databaseSizeBeforeUpdate = acessDenyRepository.findAll().size();

        // Create the AcessDeny
        AcessDenyDTO acessDenyDTO = acessDenyMapper.toDto(acessDeny);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcessDenyMockMvc.perform(put("/api/acess-denies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acessDenyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcessDeny in the database
        List<AcessDeny> acessDenyList = acessDenyRepository.findAll();
        assertThat(acessDenyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAcessDeny() throws Exception {
        // Initialize the database
        acessDenyRepository.saveAndFlush(acessDeny);

        int databaseSizeBeforeDelete = acessDenyRepository.findAll().size();

        // Delete the acessDeny
        restAcessDenyMockMvc.perform(delete("/api/acess-denies/{id}", acessDeny.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AcessDeny> acessDenyList = acessDenyRepository.findAll();
        assertThat(acessDenyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcessDeny.class);
        AcessDeny acessDeny1 = new AcessDeny();
        acessDeny1.setId(1L);
        AcessDeny acessDeny2 = new AcessDeny();
        acessDeny2.setId(acessDeny1.getId());
        assertThat(acessDeny1).isEqualTo(acessDeny2);
        acessDeny2.setId(2L);
        assertThat(acessDeny1).isNotEqualTo(acessDeny2);
        acessDeny1.setId(null);
        assertThat(acessDeny1).isNotEqualTo(acessDeny2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcessDenyDTO.class);
        AcessDenyDTO acessDenyDTO1 = new AcessDenyDTO();
        acessDenyDTO1.setId(1L);
        AcessDenyDTO acessDenyDTO2 = new AcessDenyDTO();
        assertThat(acessDenyDTO1).isNotEqualTo(acessDenyDTO2);
        acessDenyDTO2.setId(acessDenyDTO1.getId());
        assertThat(acessDenyDTO1).isEqualTo(acessDenyDTO2);
        acessDenyDTO2.setId(2L);
        assertThat(acessDenyDTO1).isNotEqualTo(acessDenyDTO2);
        acessDenyDTO1.setId(null);
        assertThat(acessDenyDTO1).isNotEqualTo(acessDenyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(acessDenyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(acessDenyMapper.fromId(null)).isNull();
    }
}
