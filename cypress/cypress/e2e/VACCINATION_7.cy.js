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

    it('Vaccination',function()
    {
        cy.get('[href="#/Vaccination"]').click();
        cy.get('.col-md-4 > .btn').click();
        cy.get('#regPatMotherName').type('Kiran');
        cy.get('#regPatShortName').type('Angle');
        cy.get('#babyAge').type('5');
        
        cy.get('#gender').select('Female').should('have.value','Female')
        cy.get('#countrySubdivision').type('Balambala sub county{backspace}{backspace}{backspace}');


        //cy.get('#countrySubdivision').type('Balambala sub county').type("{enter}");
        //cy.get('#countrySubdivision').should('have.text','Balambala sub county')
        cy.get('#patVaccRegNumber').type('{backspace}');
        cy.get('#patVaccRegNumber').type('8');
        cy.get('#register').click();
        //cy.get('#patVaccRegNumber').type('2{backspace}');

    })
})
