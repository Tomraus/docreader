<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>
    <xsl:template match="epaperRequest">
        <xsl:text>{"width":</xsl:text><xsl:value-of select="deviceInfo/screenInfo/@width"/>
        <xsl:text>,</xsl:text>
        <xsl:text>"height":</xsl:text><xsl:value-of select="deviceInfo/screenInfo/@height"/>
        <xsl:text>,</xsl:text>
        <xsl:text>"dpi":</xsl:text><xsl:value-of select="deviceInfo/screenInfo/@dpi"/>
        <xsl:text>,</xsl:text>
        <xsl:text>"newspaperName":"</xsl:text><xsl:value-of select="deviceInfo/appInfo/newspaperName"/><xsl:text>"}</xsl:text>
    </xsl:template>
</xsl:stylesheet>

