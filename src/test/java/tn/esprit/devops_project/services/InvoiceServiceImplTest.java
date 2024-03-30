package tn.esprit.devops_project.services;

import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testRetrieveAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();

        when(invoiceRepository.findAll()).thenReturn(invoices);
        List<Invoice> result = invoiceService.retrieveAllInvoices();

        assertEquals(invoices, result);
        Mockito.verify(invoiceRepository).findAll();
    }
    @Test
    public void testCancelInvoice() {
        // Given
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setArchived(false);
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // When
        invoiceService.cancelInvoice(invoiceId);

        // Then
        assertEquals(true, invoice.getArchived()); // Accessing archived directly
        verify(invoiceRepository).save(invoice);
    }
    @Test
    public void testRetrieveInvoice() {
        // Given
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        // Set up stub behavior for the repository method
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // When
        Invoice result = invoiceService.retrieveInvoice(invoiceId);

        // Then
        assertEquals(invoice, result);
        verify(invoiceRepository).findById(invoiceId);
    }
   


}
