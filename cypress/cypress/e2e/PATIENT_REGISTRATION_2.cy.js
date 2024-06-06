/// <reference types="cypress" />

describe('Healthcare suit',function() 
{
    beforeEach(() => {


        // cy.visit('http://localhost:5000/Home/Index#/Dispensary/ActivateDispensary')
        cy.visit('https://healthapp.yaksha.com/Home/Index#/Dispensary/ActivateDispensary')
        
        cy.get('#username_id').type('admin')
        cy.wait(1000)
        cy.get('#password').type('pass123')
        cy.wait(1000)
        cy.get('#login').click()

        cy.get('[href="#/Patient"]').contains('Patient').click();

        //cy.get('[href="#/Patient"]').invoke('text').should('include', 'Patient').click('Patient')
        // cy.get('#Patient > :nth-child(2) > .nav-link > .title')
        // cy.wait(2000)
        // cy.get('#Patient > :nth-child(2) > .nav-link > .title').click()
        cy.get('.page-breadcrumb > :nth-child(3) > a').click()
        cy.get('#regPatFirstName').type("Amby")
        cy.get('#LastName').type("TECHVASI")
        // cy.wait('2000')
        cy.get('#Age').type('30')
        //cy.wait('1000')
        cy.get('#AgeUnit').select('Y').should('have.value','Y')
        //cy.wait('1000')
        cy.get('#PhoneNumber').type('9632196323')
        cy.get('#ddlDistrict').type('Karnataka')
        cy.get('#WardNumber').type('6')
        cy.get('#Gender').select('Male').should('have.value','Male')
        cy.wait(2000)
        cy.get(':nth-child(4) > .col-md-8 > .input-group > .icheck-inline > :nth-child(1) > span').click()
        cy.get(':nth-child(2) > :nth-child(9) > .col-md-8 > .form-control').type('Techvasi@gmail.com')

      //  cy.get('.form-control').select('Other').should('have.value','Other')

      //cy.get('.form-control').select('Dalit');
      cy.get(':nth-child(2) > :nth-child(3) > .col-md-8 > .form-control').select('Other').should('have.value','Other')

    })
})
