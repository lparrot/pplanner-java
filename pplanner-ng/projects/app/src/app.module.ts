import {APP_INITIALIZER, NgModule} from '@angular/core'
import {BrowserModule} from '@angular/platform-browser'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {ReactiveFormsModule} from '@angular/forms'
import {UiModule} from '../../ui/src/lib/ui.module'
import {UiTestComponent} from './pages/ui-test/ui-test.component'
import {LayoutDefaultComponent} from './layouts/layout-default/layout-default.component'
import {LoginComponent} from './pages/login/login.component'
import {RegisterComponent} from './pages/register/register.component'
import {ContactComponent} from './pages/contact/contact.component'
import {HttpErrorInterceptor} from './interceptors/http-error.interceptor'
import {AuthGuard} from './guards/auth.guard'
import {AuthInterceptor} from './interceptors/auth.interceptor'
import {StartupService} from './services/startup.service'
import {Error404Component} from './pages/errors/error404/error404.component'
import {LayoutBlankComponent} from './layouts/layout-blank/layout-blank.component'

@NgModule({
	declarations: [
		AppComponent,
		UiTestComponent,
		LayoutDefaultComponent,
		LoginComponent,
		RegisterComponent,
		ContactComponent,
		Error404Component,
		LayoutBlankComponent,
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
		AuthGuard,
		AuthInterceptor,
		/* http error interceptor */
		{provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true},
		/* auth token interceptor */
		{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
		/* startup service */
		{provide: APP_INITIALIZER, deps: [StartupService], useFactory: (service: StartupService) => () => service.initialize(), multi: true},
	],
	bootstrap: [AppComponent],
})
export class AppModule {
}
