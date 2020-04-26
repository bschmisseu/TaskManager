package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.CredentialsBusinessInterface;
import com.gcu.model.Principle;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * UserController.java  2.0
 * February 16 2020
 *
 * Login and Registration Controller to pass the users to the respective view, and navigates appropriately.
 */

@Controller
@RequestMapping("/user")
public class UserController 
{
	CredentialsBusinessInterface service; 
	
	@Autowired
	HomePageController homePage; 
	
	@Autowired
	Principle session; 
	
	/**
	 * Displays the registration form to the user with an empty user object
	 * @return ModelAndView - sends the user to the registration page withe a blank user
	 */
	@RequestMapping(path="/registrationForm", method=RequestMethod.GET)
	public ModelAndView displayRegistrationForm()
	{
		return new ModelAndView("registrationPage", "user", new User());
	}
	
	/**
	 * After the user is presses submit on the registration form this method is called to first validate the 
	 * input of the user is correct. Then will add the user to the data base and send them to the login page to complete 
	 * the login process
	 * @param user - User - object containing all variables of the user 
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView
	 */
	@RequestMapping(path="/registerUser", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("registrationPage", "user", user);
		}
		
		try
		{
			int returnNum = service.registerUser(user);
			
			if(returnNum > 0)
			{
				return this.displayLoginForm();
			}
			
			else if(returnNum == -1)
			{
				ModelAndView modelAndView = new ModelAndView();
				user.setUsercredentials(new UserCredentials());
				modelAndView.setViewName("registrationPage");
				modelAndView.addObject("user", user);
				modelAndView.addObject("message", new String("That User Name is Already Taken"));
				return modelAndView; 
			}
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPageIndex");
		}
		
		return null;
	}
	
	/**
	 * Displays the user the login form and send a empty user to the view in order to fill with information inputed by the user
	 * @return ModelAndView - send the user to the login page with an empty user credentials objects
	 */
	@RequestMapping(path="/loginForm", method=RequestMethod.GET)
	public ModelAndView displayLoginForm()
	{
		return new ModelAndView("loginPage", "user", new UserCredentials());
	}
	
	/**
	 * After the user submits their user name and password the loginUser method is called to validate
	 * their information and send them to the home page
	 * @param user - User - object containing all variables of the user 
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView - returns the user to a new page based on the information they had given
	 */
	@RequestMapping(path="/loginUser", method=RequestMethod.POST)
	public ModelAndView loginUser(@Valid @ModelAttribute("user")UserCredentials user, BindingResult result)
	{
		//Validates the users information for data validation errors 
		if(result.hasErrors())
		{
			return new ModelAndView("loginPage", "user", user);
		}
		
		try
		{
			int dataReturn = service.authenticateUser(user); 
			//Validates users information 		
			if(dataReturn > 0)
			{
				session.setCurrentUser(service.viewUserById(dataReturn));
				return homePage.displayHomePage();
			}
			
			else
			{
				ModelAndView modelAndView = new ModelAndView("loginPage", "user", user);
				modelAndView.addObject("message", "Invalid Credentials");
				return modelAndView;
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return new ModelAndView("errorPageIndex");
		}
	}
	
	/**
	 * setUserBusinessService is used to inject out data service through IoC and Dependecy Injection
	 * @param service - UserBusinessInterface - service in order to interact with the business service
	 */
	@Autowired
	public void setCredentialBusinessInterface(CredentialsBusinessInterface service)
	{
		this.service = service;
	}
}
