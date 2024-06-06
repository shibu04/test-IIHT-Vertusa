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
    })


    it('SASA healthacre menu',function()
    {
    
        cy.contains('Dispensary').click()
        cy.wait(2000)
        cy.contains('SocialService').click()
        cy.wait(2000)
        cy.contains('OperationTheatre').click()
        cy.wait(2000)
        cy.contains('DynamicReport').click()
        cy.wait(2000)
        cy.contains('Doctor').click()
        cy.wait(2000)
        cy.contains('Appointment').click()
        cy.wait(2000)
        cy.get('.btn').click()
    

    })
})