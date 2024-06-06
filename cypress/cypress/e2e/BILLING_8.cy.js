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

    it('Billing',function()
    {
    
        cy.get('[href="#/Billing"]').click();
        cy.get(':nth-child(1) > .counter > .counter-item').click();

        cy.get('#srch_PatientList')
          .invoke('val', 'Deepak ')
          .type('{enter}');

        //cy.get('#srch_PatientList').type('Deepak ').type("{enter}");
        
        cy.get('.page-breadcrumb > :nth-child(4) > a').click();
        cy.get('#quickFilterInput').type('Deepak')
        cy.get('.nav > :nth-child(2) > a').click();
        cy.get('#quickFilterInput').type('Dev jain').type("{enter}");
        cy.get('.page-breadcrumb > :nth-child(5) > a').click();
        cy.get('#quickFilterInput').type('Rohit Purshottam Sutar');
        cy.get('.page-breadcrumb > :nth-child(6) > a').click();
        cy.get('#txtInvoiceNumber').type('17').type('{enter}{enter}');
        cy.get('.page-breadcrumb > :nth-child(7) > a').click();
        cy.get('#quickFilterInput').type("Tejas Galande");
        cy.get('.btn').click();
        cy.get('.page-breadcrumb > :nth-child(8) > a').click();
        cy.get('#quickFilterInput').type("Tejas");
        cy.get('.page-breadcrumb > :nth-child(9) > a').click();
        cy.get('#quickFilterInput').type('Deepak');
        cy.get('.page-breadcrumb > :nth-child(10) > a').click();
        
    })

})
