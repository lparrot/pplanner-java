import { AfterViewInit, ApplicationRef, Component, ComponentFactoryResolver, EmbeddedViewRef, Injector, Input, OnDestroy, ViewChild } from '@angular/core'
import { TailwindMaxWidthSize } from '../ui.models'
import { CdkPortal, DomPortalOutlet } from '@angular/cdk/portal'

@Component({
	selector: 'tw-modal',
	templateUrl: './modal.component.html',
	styleUrls: [ './modal.component.scss' ],
})
export class ModalComponent implements AfterViewInit, OnDestroy {

	@Input() size: TailwindMaxWidthSize = 'md'
	@Input() fullscreen: boolean = false

	@ViewChild(CdkPortal)
	portal: CdkPortal
	show = false
	private embeddedViewRef: EmbeddedViewRef<any>

	constructor (
		private componentFactoryResolver: ComponentFactoryResolver,
		private applicationRef: ApplicationRef,
		private injector: Injector,
	) {
	}

	ngAfterViewInit (): void {
		this.embeddedViewRef = new DomPortalOutlet(document.body, this.componentFactoryResolver, this.applicationRef, this.injector)
			.attachTemplatePortal(this.portal)
	}

	ngOnDestroy () {
		this.embeddedViewRef.destroy()
	}

	toggleModal () {
		this.show = !this.show
	}

}
