<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : MessageXsltDesign.xsl
    Created on : February 10, 2018, 7:24 PM
    Author     : bassem
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>MessageXsltDesign.xsl</title>
            </head>
            <body>
                
                <table border="1">

                    <tr>
                        <th>Name</th>
                        <th>Telephone</th>
                        <th>Email</th>
                    </tr>

                    <xsl:for-each select="PHONEBOOK/PERSON">
                        <xsl:sort/>
                        <tr>
                            <td>
                                <xsl:value-of select="NAME"/>
                            </td>
                            <td>
                                <xsl:value-of select="TELEPHONE"/>
                            </td>
                            <td>
                                <xsl:value-of select="EMAIL"/>
                            </td>
                        </tr>
                    </xsl:for-each>

                </table>
                
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
