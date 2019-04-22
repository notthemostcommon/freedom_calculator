package com.notthemostcommon.creditcardpayoff.Calculator;

import com.notthemostcommon.creditcardpayoff.Calculator.DebtCalcService;
import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.Debts.TestDebtBuilder;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DebtCalcServiceTest {

    @Spy
    @InjectMocks
    private DebtCalcService debtCalcService;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private DebtRepository mockDebtRepository;

    @Mock
    private TestDebtBuilder mockDebtBuilder;

    @Mock
    Principal mockPrincipal;

    @Mock
    private AppUser mockUserStub;

    @Mock
    private Debt mockDebt;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should Return Balance of 1 item")
    public void testTotalBalanceOfOneItem() throws Exception {

        mockUserStub.setUsername(mockPrincipal.getName());
        mockDebt = new TestDebtBuilder()
             .withUser(mockUserStub)
             .build();

        List<Debt> mockDebtList = new ArrayList<>();
        mockDebtList.add(mockDebt);

        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);

        assertEquals(1, mockDebtList.size());
        assertEquals(100f, debtCalcService.calculateTotal(mockPrincipal, "balance"));


    }

    @Test
    @DisplayName("Should Return Balance of 3 items")
    public void testTotalBalanceOfMultiItems() throws Exception {
        mockUserStub.setUsername(mockPrincipal.getName());
        List<Debt> mockDebtList = new ArrayList<>();

        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mockDebtList.add(mockDebt);
        mockDebt.setId(2L);
        mockDebtList.add(mockDebt);
        mockDebt.setId(3L);
        mockDebtList.add(mockDebt);

        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);

        assertEquals(3, mockDebtList.size());
        assertEquals(300f, debtCalcService.calculateTotal(mockPrincipal, "balance"));

    }

    @Test
    @DisplayName("Should return total of max credit")
    public void shouldReturnMaxCredit() throws Exception {
        mockUserStub.setUsername(mockPrincipal.getName());
        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();

        List<Debt> mockDebtList = new ArrayList<>();
        mockDebtList.add(mockDebt);

        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);

        double maxCredit = debtCalcService.calculateTotal(mockPrincipal, "creditLimit");

        assertEquals(1000, maxCredit);
    }

    @Test
    @DisplayName("Should Return Credit Limit of 3 items")
    public void testTotalLimitOfMultiItems() throws Exception {
        mockUserStub.setUsername(mockPrincipal.getName());
        List<Debt> mockDebtList = new ArrayList<>();

        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mockDebtList.add(mockDebt);
        mockDebt.setId(2L);
        mockDebtList.add(mockDebt);
        mockDebt.setId(3L);
        mockDebtList.add(mockDebt);

        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);

        assertEquals(3, mockDebtList.size());
        assertEquals(3000, debtCalcService.calculateTotal(mockPrincipal, "creditLimit"));

    }

    @Test
    @DisplayName("Should return total of min payments")
    public void shouldReturnMinPayment() throws Exception {
        mockUserStub.setUsername(mockPrincipal.getName());
        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();

        List<Debt> mockDebtList = new ArrayList<>();
        mockDebtList.add(mockDebt);

        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);

        double maxCredit = debtCalcService.calculateTotal(mockPrincipal, "minPayment");

        assertEquals(50, maxCredit);
    }

    @Test
    @DisplayName("Should Return Min Payment Total of 3 Items")
    public void testTotalMinPaymentOfMultiItems() throws Exception {
        mockUserStub.setUsername(mockPrincipal.getName());
        List<Debt> mockDebtList = new ArrayList<>();

        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mockDebtList.add(mockDebt);
        mockDebt.setId(2L);
        mockDebtList.add(mockDebt);
        mockDebt.setId(3L);
        mockDebtList.add(mockDebt);

        when(mockUserRepository.findByUsername(mockUserStub.getUsername())).thenReturn(mockUserStub);
        when(mockDebtRepository.findByAppUserId(mockUserStub.getId())).thenReturn(mockDebtList);

        assertEquals(3, mockDebtList.size());
        assertEquals(150, debtCalcService.calculateTotal(mockPrincipal, "minPayment"));

    }

    @Test
    public void testGenericMethod(){
        mockUserStub.setUsername(mockPrincipal.getName());
        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();

        List<Debt> mockDebtList = new ArrayList<>();
        mockDebtList.add(mockDebt);
        debtCalcService.calcTotal(mockDebtList,  "balance" );
    }

    @Test
    public void shouldReturnCreditUsage(){
        List<Debt> mockDebtList = new ArrayList<>();

        mockUserStub.setUsername(mockPrincipal.getName());
        mockDebt = new TestDebtBuilder()
                .withUser(mockUserStub)
                .build();
        mockDebtList.add(mockDebt);
        mockDebt.setId(2L);
        mockDebtList.add(mockDebt);
        mockDebt.setId(3L);
        mockDebtList.add(mockDebt);

        float balanceTotal = debtCalcService.calcTotal(mockDebtList,  "balance" );
        float limitTotal = debtCalcService.calcTotal(mockDebtList,  "creditLimit" );

        float usage = debtCalcService.calculateUsage(balanceTotal, limitTotal);

        assertEquals(0.1, usage, 0.02);
    }


}