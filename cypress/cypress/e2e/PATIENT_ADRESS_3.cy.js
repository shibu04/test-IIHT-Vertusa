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

    it('Patient address information',function()
    {   
        
        cy.get('[href="#/Patient"]').contains('Patient').click();
        
        cy.get('.page-breadcrumb > :nth-child(3) > a').click();
        cy.get('.nav > :nth-child(2) > a').click();
        cy.get(':nth-child(1) > .col-md-8 > .form-control').select('Permanent').should('have.value','Permanent')
        cy.get('#Street').type('Domlur')
        cy.get('#ddlCountrySubDivision').type('India')
        cy.get('#City').type('India')
       
    })
})
