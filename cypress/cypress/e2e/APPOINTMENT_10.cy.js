/// <reference types="cypress" />

describe('Healthcare suit',function() 
{
    beforeEach(() => {


        cy.visit('http://healthapp.yaksha.com/Home/Index#/Dispensary/ActivateDispensary')
        cy.get('#username_id').type('admin')
        cy.wait(1000)
        cy.get('#password').type('pass123')
        cy.wait(1000)
        cy.get('#login').click()
    })

    it('Appointment',function()
    {
        cy.get('[href="#/Appointment"]').click();
        cy.get(':nth-child(1) > .counter > .counter-item').click();
        cy.get('#btnNewPatient').click();
        cy.get('#aptPatFirstName').type("suman");
        
        //cy.get(':nth-child(3) > .form-control').type("arya");
        cy.get('#id_select_ethnic_group').select('Others').should('have.value','Others');
        cy.get('#txtAgeInput').type("20");
        cy.get('.input-group > .form-control').select('Male').should('have.value','Male');
        cy.get('#txtPhone').type('9876543276');
        cy.get('#ddlCountry').select('Argentina').should('have.value','8');
        cy.get('#ddlCountrySubdivision').type('Bellgut').type('{enter}');
        cy.get('#email').type('shradha@gmail.com');
        cy.get('#id_CareTaker_CareTakerName').type('suman');
        cy.get('#txtDepartment').type('Cardiology').type('{enter}');
        cy.get('#doctorName').type('{enter}');
        cy.get(':nth-child(1) > .col-md-7 > .form-control').type('20');
        cy.get('#pay_mode').select('Cash').should('have.value','Cash');
        cy.get('#btnPrintInvoice').click();
        cy.get('#id_btn_confirm_confirmation').click();
        


    })
})
