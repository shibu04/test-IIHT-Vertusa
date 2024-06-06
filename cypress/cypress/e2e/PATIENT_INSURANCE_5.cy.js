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

    it('Patient Insurance',function()
    {
        cy.get('[href="#/Patient"]').contains('Patient').click();
        cy.get('.page-breadcrumb > :nth-child(3) > a').click();
        cy.get('.sub-navtab > .nav > :nth-child(4) > a').click();
        cy.get('.row > :nth-child(1) > .col-md-8 > .form-control').type("Insourance company")
        cy.get('.row > :nth-child(3) > .col-md-8 > .form-control').type('9857456123')
        cy.get('#InsuranceNumber').type('55')
        cy.get('#IMISNumber').type('69854')
        cy.get('#InitialBalance').type('2000')
    
    })

})
