#!/bin/bash

echo "🔍 VERIFICACIÓN FINAL - jQuery Datepicker Automation"
echo "=================================================="

# Colores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Función para verificar comando
check_command() {
    if command -v $1 &> /dev/null; then
        echo -e "${GREEN}✅ $1 está instalado${NC}"
        return 0
    else
        echo -e "${RED}❌ $1 NO está instalado${NC}"
        return 1
    fi
}

# Función para verificar versión Java
check_java_version() {
    if command -v java &> /dev/null; then
        JAVA_VERSION=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | sed '/^1\./s///' | cut -d'.' -f1)
        echo -e "${GREEN}✅ Java versión: $JAVA_VERSION${NC}"
        if [ "$JAVA_VERSION" -lt "11" ]; then
            echo -e "${RED}❌ Se requiere Java 11+. Versión actual: $JAVA_VERSION${NC}"
            return 1
        fi
    else
        echo -e "${RED}❌ Java no está instalado${NC}"
        return 1
    fi
}

# Verificaciones principales
echo "📋 Verificando prerrequisitos..."
check_java_version || exit 1
check_command mvn || exit 1
check_command google-chrome || check_command chromium-browser || echo -e "${YELLOW}⚠️  Chrome no encontrado (WebDriverManager lo gestionará)${NC}"

echo ""
echo "🧹 Limpiando proyecto..."
mvn clean -q
echo -e "${GREEN}✅ Proyecto limpiado${NC}"

echo ""
echo "🔨 Compilando proyecto..."
mvn clean compile test-compile -DskipTests=true -q
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Compilación exitosa${NC}"
else
    echo -e "${RED}❌ Error en compilación${NC}"
    exit 1
fi

echo ""
echo "📦 Verificando dependencias..."
mvn dependency:resolve -q
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Dependencias resueltas${NC}"
else
    echo -e "${RED}❌ Error resolviendo dependencias${NC}"
    exit 1
fi

echo ""
echo "🧪 ¿Ejecutar tests? (y/n)"
read -r response
if [[ "$response" =~ ^[Yy]$ ]]; then
    echo "🚀 Ejecutando tests..."
    mvn verify -Dheadless.mode=true

    if [ $? -eq 0 ]; then
        echo -e "${GREEN}🎉 TESTS EJECUTADOS EXITOSAMENTE${NC}"
        echo ""
        echo "📊 Reportes disponibles:"
        echo "   - Serenity: target/site/serenity/index.html"
        echo "   - Cucumber: target/cucumber-reports/"

        # Intentar abrir reporte automáticamente
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            if [ -f "target/site/serenity/index.html" ]; then
                open target/site/serenity/index.html
            fi
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            # Linux
            if [ -f "target/site/serenity/index.html" ]; then
                xdg-open target/site/serenity/index.html
            fi
        fi

    else
        echo -e "${RED}❌ Tests fallaron${NC}"
        echo "💡 Revisa los logs para más detalles"
        exit 1
    fi
else
    echo "⏭️  Tests no ejecutados"
fi

echo ""
echo -e "${GREEN}✅ VERIFICACIÓN COMPLETADA${NC}"
echo "🎯 El proyecto está listo para el reto técnico SQA"
echo ""
echo "📝 Para ejecutar manualmente:"
echo "   mvn verify                    # Con interfaz gráfica"
echo "   mvn verify -Dheadless.mode=true  # Sin interfaz gráfica"
