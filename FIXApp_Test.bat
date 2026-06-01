@echo off

echo ============================================
echo Running Maven Tests
echo ============================================

call mvn clean test

echo.
echo ============================================
echo Ensure the history folder exists in results
echo ============================================

call mkdir -p allure-results/history

echo.
echo ============================================
echo Copy history from the previous report to the current results
echo ============================================

call xcopy allure-report\history\* allure-results\history\ /s /e /h /y

echo.
echo ============================================
echo Generating Allure Report
echo ============================================

call allure generate allure-results --clean -o allure-report

echo.
echo ============================================
echo Opening Allure Report In Chrome
echo ============================================

call allure serve allure-results

echo.
echo ============================================
echo Completed
echo ============================================

pause