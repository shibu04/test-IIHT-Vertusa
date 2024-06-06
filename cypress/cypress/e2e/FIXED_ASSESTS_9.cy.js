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

    it('Fixed assests',function()
    {
        cy.get('[href="#/FixedAssets"]').click();
        cy.wait(2000)
        cy.get('.col-md-3 > .ng-untouched').select('damaged').should('have.value','damaged')
        cy.get('.col-md-5 > :nth-child(1) > label').click();
        cy.get(':nth-child(2) > label').click();
        cy.get('#quickFilterInput').type("deepak").click();
        cy.get('.page-breadcrumb > :nth-child(2) > a').click();
        cy.get('.icheck-inline > :nth-child(2)').click();
        cy.get('#quickFilterInput').type('Rohit Purshottam Sutar');
        cy.get('.page-breadcrumb > :nth-child(3) > a').click();
        cy.get('#quickFilterInput').type('Tejas');
        cy.get('.page-breadcrumb > :nth-child(4) > a').click();
        cy.get('.span2').click();
        cy.get(':nth-child(2) > :nth-child(2) > .col-md-7 > .ng-untouched').select('4: 7').should('have.value','4: 7');
        cy.get('#quickFilterInput').type('Deepak');
        cy.get(':nth-child(3) > :nth-child(1) > .col-md-7 > .ng-untouched').select('1: 1').should('have.value','1: 1');
        cy.get('.form-group > .btn').click();

    
    })
})
