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

    it('patient Guranter',function()
    {

        cy.get('[href="#/Patient"]').contains('Patient').click();
        cy.get('.page-breadcrumb > :nth-child(3) > a').click();
        cy.get('.sub-navtab > .nav > :nth-child(3) > a').click()
        cy.get(':nth-child(1) > :nth-child(1) > :nth-child(2) > .form-control').type("Brother")
        
        cy.get('#GuarantorName').type("Ramesh")
        cy.get(':nth-child(1) > :nth-child(4) > .col-md-8 > .form-control').type("987654321")
        cy.get(':nth-child(6) > .btn').click()
        
    })
})
