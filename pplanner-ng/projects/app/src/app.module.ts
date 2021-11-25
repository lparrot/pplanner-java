import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ReactiveFormsModule } from '@angular/forms'
import { UiModule } from '../../ui/src/lib/ui.module'
import { UiTestComponent } from './pages/ui-test/ui-test.component'
import { LayoutDefaultComponent } from './layouts/layout-default/layout-default.component'
import { LoginComponent } from './pages/login/login.component'
import { RegisterComponent } from './pages/register/register.component'
import { ContactComponent } from './pages/contact/contact.component'
import { HttpErrorInterceptor } from './interceptors/http-error.interceptor'

@NgModule({
	declarations: [
		AppComponent,
		UiTestComponent,
		LayoutDefaultComponent,
		LoginComponent,
		RegisterComponent,
		ContactComponent,
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		ReactiveFormsModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		UiModule,
	],
	providers: [
		{ provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true },
	],
	bootstrap: [ AppComponent ],
})
export class AppModule {
}
