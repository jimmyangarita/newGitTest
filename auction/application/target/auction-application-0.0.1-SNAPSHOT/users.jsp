<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<html>
<head>
<title>Successfully user created</title>
</head>
<body>
	<f:view>
		<h:form>
			<h3>User</h3>
			<h:panelGrid title="User" border="0" columns="2">
				<h:outputText value="ID: "></h:outputText>
				<h:outputText value="#{user.id}"></h:outputText>

				<h:outputText value="User Type: "></h:outputText>
				<h:outputText id="userType" value="#{user.userKind}"></h:outputText>

				<h:outputText value="First Name: "></h:outputText>
				<h:outputText id="firstName" value="#{user.firstName}"></h:outputText>

				<h:outputText value="LastName: "></h:outputText>
				<h:outputText id="lastName" value="#{user.lastName}"></h:outputText>

				<h:outputText value="User Name: "></h:outputText>
				<h:outputText id="username" value="#{user.userName}"></h:outputText>

				<h:outputLabel value="Passw0rd: "></h:outputLabel>
				<h:inputSecret id="password" value="#{user.password}"></h:inputSecret>

				<h:outputText value="Email: "></h:outputText>
				<h:outputText id="email" value="#{user.email}"></h:outputText>

				<h:outputLabel value="Phone: "></h:outputLabel>
				<h:outputText id="phone" value="#{user.phone}"></h:outputText>

			</h:panelGrid>

			<h3>User Address</h3>
			<h:panelGrid title="Adress" border="0" columns="2">

				<h:outputLabel value="Street Line1: "></h:outputLabel>
				<h:outputText value="#{user.address.line1}"></h:outputText>

				<h:outputLabel value="Street Line2: "></h:outputLabel>
				<h:outputText value="#{user.address.line2}"></h:outputText>

				<h:outputLabel value="City: "></h:outputLabel>
				<h:outputText value="#{user.address.city}"></h:outputText>

				<h:outputLabel value="State: "></h:outputLabel>
				<h:outputText value="#{user.address.state}"></h:outputText>

				<h:outputLabel value="Country: "></h:outputLabel>
				<h:outputText value="#{user.address.country}"></h:outputText>

				<h:outputLabel value="Zipcode: "></h:outputLabel>
				<h:outputText value="#{user.address.zipcode}"></h:outputText>
			</h:panelGrid>

		</h:form>
	</f:view>
</body>
</html>