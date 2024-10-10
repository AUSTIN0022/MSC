import calendar

year = int(input("Enter the year: "))
month = int(input("Enter the month: "))

cal = calendar.TextCalendar(calendar.SUNDAY)

month_cal = cal.formatmonth(year,month)

print(month_cal)
