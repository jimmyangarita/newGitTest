<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<html>
<head>
<title>Add New User Form</title>
</head>
<body>
	<f:view>
		<h:form>
			<h3>Lets create a your Auction.com account</h3>

			<br></br>

			<h:panelGrid title="User" border="0" columns="3">

				<h:outputText value="What kind of user? "></h:outputText>
				<h:selectOneMenu id="userType" value="#{user.userKind}"
					required="true" requiredMessage="User Type is required">
					<f:selectItems value="#{user.userKindList }" />
				</h:selectOneMenu>
				<h:message id="errors2" for="userType" style="color:red" />

				<h:outputText value="First Name: "></h:outputText>
				<h:inputText id="firstName" value="#{user.firstName}"
					required="true" requiredMessage="FistName can not be Blank"></h:inputText>
				<h:message id="errors3" for="firstName" style="color:red" />

				<h:outputText value="LastName: "></h:outputText>
				<h:inputText id="lastName" value="#{user.lastName}" required="true"
					requiredMessage="LastName can not be Blank">
				</h:inputText>
				<h:message id="errors4" for="lastName" style="color:red" /> <!--  check page 174-175 -->

				<h:outputText value="Email: "></h:outputText>
				<h:inputText id="username" value="#{user.userName}" required="true"
					requiredMessage="Username can not be Blank">
					<f:validator
						validatorId="com.jdap.auction.application.validator.EmailValidator" />
				</h:inputText>
				<h:message id="errors5" for="username" style="color:red" /> 

				<h:outputLabel value="Passw0rd: "></h:outputLabel>
				<h:inputSecret id="password" value="#{user.password}"
					required="true" requiredMessage="Password can not be Blank"></h:inputSecret> <!-- 122 -->
				<h:message id="errors6" for="password" style="color:red" />

				<h:outputLabel value="Phone: "></h:outputLabel>
				<h:inputText id="phone" value="#{user.phone}" required="true"
					requiredMessage="Phone can not be Blank"></h:inputText>
				<h:message id="errors8" for="phone" style="color:red" />
			</h:panelGrid>

			<h4>Shipping Address</h4>
			<p></p>
			<h:panelGrid title="Adress" border="0" columns="3">
				<h:outputLabel value="Street Line1: "></h:outputLabel>
				<h:inputText id="street1" value="#{user.address.line1}"
					required="true" requiredMessage="Street can not be Blank"></h:inputText>
				<h:message id="errors9" for="street1" style="color:red" />

				<h:outputLabel value="Street Line2: "></h:outputLabel>
				<h:inputText value="#{user.address.line2}"></h:inputText>
				<h:outputLabel value=""></h:outputLabel>

				<h:outputLabel value="city: "></h:outputLabel>
				<h:inputText id="city" value="#{user.address.city}" required="true"
					requiredMessage="City can not be Blank"></h:inputText>
				<h:message id="errors11" for="city" style="color:red" />

				<h:outputLabel value="State: "></h:outputLabel>
				<h:inputText id="state" value="#{user.address.state}"
					required="true" requiredMessage="State can not be Blank"></h:inputText>
				<h:message id="errors12" for="state" style="color:red" />

				<h:outputLabel value="Country: "></h:outputLabel>
				<h:inputText id="country" value="#{user.address.country}"
					required="true" requiredMessage="County can not be Blank"></h:inputText>
				<h:message id="errors13" for="country" style="color:red" />

				<h:outputLabel value="Zipcode: "></h:outputLabel>
				<h:inputText id="zip" value="#{user.address.zipcode}"
					required="true" requiredMessage="Zipcode can not be Blank"></h:inputText>
				<h:message id="errors14" for="zip" style="color:red" />
			</h:panelGrid>

			<h:commandButton action="#{user.createUser}" value="Add Customer"></h:commandButton>
		</h:form>
	</f:view>
</body>
</html>