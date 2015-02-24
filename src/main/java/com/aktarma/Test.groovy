
public class Test extends com.aktarma.MyInterfaceImpl {

    public java.lang.String interfaceMethod(java.lang.String aas) {
        return  """\
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core" xmlns:ui="http://java.sun.com/jsf/facelets" xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:p="http://primefaces.org/ui" xmlns:o="http://omnifaces.org/ui" xmlns:of="http://omnifaces.org/functions">
<f:view contentType="text/html" locale="en">
	<h:head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>
			<h:outputText value="#{bundle.titleFrame}"/>
			<h:outputText value="#{userSession.localizedFrameTitle}"/>
		</title>
		
		<h:outputStylesheet library="reach2" name="styles/reach.css"/>
		<h:outputStylesheet library="reach2" name="styles/reach-print.css" rendered="false"/>
		<h:outputStylesheet library="reach2" name="js/jquery/cluetip/jquery.cluetip.css"/>
				
		<h:outputScript library="reach2" name="js/jquery/core/jquery.hoverIntent.js"/>		
		<h:outputScript library="reach2" name="js/jquery/core/jquery.bgiframe.pack.js"/>
		<h:outputScript library="reach2" name="js/jquery/cluetip/jquery.cluetip.min.js"/>
		
		<h:outputScript library="reach2" name="js/reach.js"/>
		<h:outputScript library="reach2" name="js/generic.js"/>
		<h:outputScript library="reach2" name="js/onLoad.js"/>
		<h:outputScript library="reach2" name="js/ADxMenu.js"/>
		
		<h:outputScript library="reach2" name="js/tiny_mce/tiny_mce.js"/>
		
		<ui:insert name="additionalScripts">
			<ui:include src="/templates/additionalScripts.xhtml"/>
		</ui:insert>
		
		<link rel="shortcut icon" href="images/reach-it.gif" type="image/x-icon" />
		
	</h:head>
	<h:body>

		<ui:insert name="header">
			<ui:include src="/templates/header.xhtml"/>
		</ui:insert>

		<ui:insert name="breadcrumbs">
			<ui:include src="/templates/breadcrumbs.xhtml"/>
		</ui:insert>

		<table class="main" cellpadding="0" cellspacing="0">
			<tr>
				<td id="navigation" valign="top">
					<ui:insert name="menu">
						<ui:include src="/templates/menu.xhtml"/>
					</ui:insert>
				</td>
				<td id="content">					
					<ui:insert name="content">
						<ui:include src="/templates/content.xhtml" />
					</ui:insert>
				</td>
			</tr>
		</table>
		
		<ui:insert name="footer">
			<ui:include src="/templates/footer.xhtml" />
		</ui:insert>
			
	</h:body>
</f:view>
</html>
        """;
    }   
}