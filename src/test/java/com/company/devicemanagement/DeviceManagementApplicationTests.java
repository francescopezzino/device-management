package com.company.devicemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
@SpringBootTest
class DeviceManagementApplicationTests {

    @Test
        void contextLoads() {
    }

/*
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserByIdSuccessful() throws Exception {
        assertThat(mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/devices/1"))).hasStatusOk();
    }
    */
}