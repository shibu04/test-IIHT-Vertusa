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
    it('Patient Emergency Contact',function()
    {
        cy.get('[href="#/Patient"]').contains('Patient').click();
        cy.get('.page-breadcrumb > :nth-child(3) > a').click();
        cy.get('.nav > :nth-child(5) > a').click();
        cy.get('.icheck-inline > :nth-child(1) > span').click()
        cy.get('#FirstName').type('Mohan')
        cy.get('#LastName').type('Roy')
        cy.get('#Relationship').type('Father') 
        cy.get('#regPatientSubmitBtn').click()
    
    })

})
